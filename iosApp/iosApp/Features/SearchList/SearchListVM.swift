//
//  SearchListViewModel.swift
//  iosApp
//
//  Created by Dzmitry Chyrta on 18.11.2022.
//  Copyright © 2022 orgName. All rights reserved.
//

import Foundation
import LivesportKit
import SwiftUI
import Combine

class SearchListVM: SearchViewModel, ObservableObject  {
    @Published var state: SearchContractState = SearchContractState.companion.idle()
    @Published var hideKeyboard: Bool = false

    override init() {
        super.init()
        collect(flow: uiState, collect: { state in
            self.state = state as! SearchContractState
        })
        
        collect(flow: effect) { uiEffect in
            let effect = (uiEffect as! SearchContractEffect)
            switch effect {
            case is SearchContractEffectHideKeyboard:
                self.hideKeyboard = false
            default:
                self
            }
        }
    }
    
}
